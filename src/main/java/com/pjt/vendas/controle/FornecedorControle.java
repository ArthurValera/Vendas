package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Fornecedor;
import com.pjt.vendas.repositorios.CidadeRep;
import com.pjt.vendas.repositorios.FornecedorRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FornecedorControle {
    @Autowired
    private FornecedorRep fornecedorRepRep;
    @Autowired
    private CidadeRep cidadeRep;

    @GetMapping("/cadastraFornecedor")
    public ModelAndView cadastrar(Fornecedor fornecedor){
        ModelAndView mv = new ModelAndView("/adm/fornecedores/cadastro");
        mv.addObject("fornecedor", fornecedor);
        mv.addObject("listaCidade", cidadeRep.findAll());
        return mv;
    }

    @GetMapping("/listaFornecedor")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/fornecedores/lista");
        mv.addObject("listaFornecedor", fornecedorRepRep.findAll());
        return mv;
    }

    @GetMapping("/editaFornecedor/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepRep.findById(id);

        if (fornecedor.isPresent()) {
            return cadastrar(fornecedor.get());
        } else {
            ModelAndView mv = new ModelAndView("redirect:/listaFornecedor");
            mv.addObject("errorMessage", "Fornecedor não encontrado!");
            return mv;
        }
    }


    @GetMapping("/removeFornecedor/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Fornecedor> fornecedor = fornecedorRepRep.findById(id);
        fornecedorRepRep.delete(fornecedor.get());
        return listar();
    }

    @PostMapping("/salvaFornecedor")
    public ModelAndView salvar(Fornecedor fornecedor, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(fornecedor);
        }
        fornecedorRepRep.saveAndFlush(fornecedor);
        return cadastrar(new Fornecedor());
    }
}
