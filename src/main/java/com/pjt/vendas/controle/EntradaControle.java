package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.EntradaProd;
import com.pjt.vendas.modelos.ItemEntrada;
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
public class EntradaControle {
    @Autowired
    private EntradaRep entradaRep;
    @Autowired
    private ItemEntradaRep itemEntradaRep;
    @Autowired
    private ProdutoRep produtoRep;
    @Autowired
    private FuncionarioRep funcionarioRep;
    @Autowired
    private FornecedorRep fornecedorRep;

    private List<ItemEntrada> listaItemEntrada = new ArrayList<ItemEntrada>();

    @GetMapping("/cadastraEntradaProd")
    public ModelAndView cadastrar(EntradaProd entradaProd, ItemEntrada itemEntrada){
        ModelAndView mv = new ModelAndView("/adm/entradaProds/cadastro");
        mv.addObject("entradaProd", entradaProd);
        mv.addObject("itemEntrada", itemEntrada);
        mv.addObject("listaItemEntrada", this.listaItemEntrada);
        mv.addObject("listaFuncionarios", funcionarioRep.findAll());
        mv.addObject("listaFornecedores", fornecedorRep.findAll());
        mv.addObject("listaProdutos", produtoRep.findAll());
        return mv;
    }

    @GetMapping("/listaEntradaProd")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/entradaProds/lista");
        mv.addObject("listaEntradaProd", entradaRep.findAll());
        return mv;
    }

    @GetMapping("/editaEntradaProd/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<EntradaProd> entradaProd = entradaRep.findById(id);
        this.listaItemEntrada = itemEntradaRep.buscarPorEntrada(id);
        return cadastrar(entradaProd.get(), new ItemEntrada());
    }

//    @GetMapping("/removeEntradaProd/{id}")
//    public ModelAndView remover(@PathVariable("id") Long id) {
//        Optional<EntradaProd> entradaProd = entradaRep.findById(id);
//        entradaRep.delete(entradaProd.get());
//        return listar();
//    }

    @PostMapping("/salvaEntradaProd")
    public ModelAndView salvar(String acao, EntradaProd entradaProd, ItemEntrada itemEntrada, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(entradaProd, itemEntrada);
        }
        if (acao.equals("itens")) {
            this.listaItemEntrada.add(itemEntrada);
            entradaProd.setValorTotal((entradaProd.getValorTotal() + itemEntrada.getPreco()) *itemEntrada.getQuantidade());
            entradaProd.setQuantidadeTotal(entradaProd.getQuantidadeTotal() + itemEntrada.getQuantidade());

            this.listaItemEntrada.add(itemEntrada);
        } else if (acao.equals("salvar")) {
            entradaRep.saveAndFlush(entradaProd);

            for(ItemEntrada it: listaItemEntrada){
            it.setEntradaProd(entradaProd);
            itemEntradaRep.saveAndFlush(it);

            Optional<Produto> prod = produtoRep.findById(it.getProduto().getId());
            Produto produto = prod.get();

            produto.setEstoque(produto.getEstoque() + it.getQuantidade());
            produto.setPreco(it.getPreco());
            produto.setCusto(it.getCusto());
            produtoRep.saveAndFlush(produto);

            this.listaItemEntrada = new ArrayList<>();
            }
            entradaRep.saveAndFlush(entradaProd);
            return cadastrar(new EntradaProd(), new ItemEntrada());
        }
        return cadastrar(entradaProd, new ItemEntrada());
    }

    public List<ItemEntrada> getListaItemEntrada() {
        return listaItemEntrada;
    }

    public void setListaItemEntrada(List<ItemEntrada> listaItemEntrada) {
        this.listaItemEntrada = listaItemEntrada;
    }
}
