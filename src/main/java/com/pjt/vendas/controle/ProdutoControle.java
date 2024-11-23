package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Produto;
import com.pjt.vendas.repositorios.ProdutoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ProdutoControle {
    @Autowired
    private ProdutoRep produtoRep;

    @GetMapping("/cadastraProduto")
    public ModelAndView cadastrar(Produto produto){
        ModelAndView mv = new ModelAndView("/adm/produtos/cadastro");
        mv.addObject("produto", produto);
        return mv;
    }

    @GetMapping("/listaProduto")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/produtos/lista");
        mv.addObject("listaProduto", produtoRep.findAll());
        return mv;
    }

    @GetMapping("/editaProduto/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoRep.findById(id);
        return cadastrar(produto.get());
    }

    @GetMapping("/removeProduto/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Produto> produto = produtoRep.findById(id);
        produtoRep.delete(produto.get());
        return listar();
    }

    @PostMapping("/salvaProduto")
    public ModelAndView salvar(Produto produto, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(produto);
        }
        produtoRep.saveAndFlush(produto);
        return cadastrar(new Produto());
    }
}
