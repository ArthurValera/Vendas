package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Cidade;
import com.pjt.vendas.repositorios.CidadeRep;
import com.pjt.vendas.repositorios.EstadoRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class CidadeControle {
    @Autowired
    private CidadeRep cidadeRep;
    @Autowired
    private EstadoRep estadoRep;

    @GetMapping("/cadastraCidade")
    public ModelAndView cadastrar(Cidade cidade){
        ModelAndView mv = new ModelAndView("/adm/cidades/cadastro");
        mv.addObject("cidade", cidade);
        mv.addObject("listaEstado", estadoRep.findAll());
        return mv;
    }

    @GetMapping("/listaCidade")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/cidades/lista");
        mv.addObject("listaCidade", cidadeRep.findAll());
        return mv;
    }

    @GetMapping("/editaCidade/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRep.findById(id);
        return cadastrar(cidade.get());
    }

    @GetMapping("/removeCidade/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cidade> cidade = cidadeRep.findById(id);
        cidadeRep.delete(cidade.get());
        return listar();
    }

    @PostMapping("/salvaCidade")
    public ModelAndView salvar(Cidade cidade, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(cidade);
        }
        cidadeRep.saveAndFlush(cidade);
        return cadastrar(new Cidade());
    }
}
