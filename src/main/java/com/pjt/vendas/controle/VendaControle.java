package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Venda;
import com.pjt.vendas.modelos.ItemVenda;
import com.pjt.vendas.modelos.Produto;
import com.pjt.vendas.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class VendaControle {
    @Autowired
    private VendaRep vendaRep;
    @Autowired
    private ItemVendaRep itemVendaRep;
    @Autowired
    private ProdutoRep produtoRep;
    @Autowired
    private FuncionarioRep funcionarioRep;
    @Autowired
    private ClienteRep clienteRep;

    private List<ItemVenda> listaItemVenda = new ArrayList<ItemVenda>();

    @GetMapping("/cadastraVenda")
    public ModelAndView cadastrar(Venda venda, ItemVenda itemVenda) {
        ModelAndView mv = new ModelAndView("/adm/vendas/cadastro");
        mv.addObject("venda", new Venda());
        mv.addObject("itemVenda", new ItemVenda());
        mv.addObject("listaProdutos", produtoRep.findAll());
        mv.addObject("listaFuncionarios", funcionarioRep.findAll());
        mv.addObject("listaClientes", clienteRep.findAll());
        mv.addObject("listaItemVenda", listaItemVenda); // Adicionando lista de itens à página
        return mv;
    }


    @GetMapping("/listaVenda")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/vendas/lista");
        mv.addObject("listaVenda", vendaRep.findAll());
        return mv;
    }

    @GetMapping("/removeVenda/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        // Remover todos os itens da venda antes de excluir a venda
        List<ItemVenda> itensVenda = itemVendaRep.buscarPorVenda(id);
        for (ItemVenda item : itensVenda) {
            itemVendaRep.delete(item);  // Exclui os itens da venda
        }

        // Agora que os itens foram removidos, podemos excluir a venda
        Optional<Venda> venda = vendaRep.findById(id);
        vendaRep.delete(venda.get());

        return listar();  // Redireciona para a lista de vendas após remoção
    }


    @PostMapping("/salvaVenda")
    public ModelAndView salvar(String acao, Venda venda, ItemVenda itemVenda, BindingResult result) {
        if (result.hasErrors()) {
            return cadastrar(venda, itemVenda);
        }

        if (acao.equals("itens")) {
            // Atualiza os valores do itemVenda
            itemVenda.setValor(itemVenda.getProduto().getPreco());
            itemVenda.setSubtotal(itemVenda.getProduto().getPreco() * itemVenda.getQuantidade());

            // Atualiza os valores totais da venda antes de adicionar o item
            venda.setValorTotal(venda.getValorTotal() + itemVenda.getSubtotal());
            venda.setQuantidadeTotal(venda.getQuantidadeTotal() + itemVenda.getQuantidade());

            // Persistir a venda com o valor atualizado antes de adicionar o item
            vendaRep.saveAndFlush(venda); // Atualiza a venda no banco com o valor total

            // Adicionando o item à lista de itens
            this.listaItemVenda.add(itemVenda);
        } else if (acao.equals("salvar")) {
            // Salva a venda com o valor total atualizado
            vendaRep.saveAndFlush(venda);  // Garanta que a venda seja salva após o cálculo do valor total

            for (ItemVenda it : listaItemVenda) {
                it.setVenda(venda);
                it.setSubtotal(it.getValor() * it.getQuantidade());
                itemVendaRep.saveAndFlush(it);

                // Atualiza o estoque do produto
                Optional<Produto> prod = produtoRep.findById(it.getProduto().getId());
                Produto produto = prod.get();
                produto.setEstoque(produto.getEstoque() - it.getQuantidade());
                produto.setPreco(it.getValor());
                produtoRep.saveAndFlush(produto);
            }

            // Limpa a lista de itens após salvar a venda
            this.listaItemVenda = new ArrayList<>();
            return cadastrar(new Venda(), new ItemVenda());
        }

        return cadastrar(venda, new ItemVenda());
    }



    public List<ItemVenda> getListaItemVenda() {
        return listaItemVenda;
    }

    public void setListaItemVenda(List<ItemVenda> listaItemVenda) {
        this.listaItemVenda = listaItemVenda;
    }
}
