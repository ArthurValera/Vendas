package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Cidade;
import com.pjt.vendas.modelos.Funcionario;
import com.pjt.vendas.repositorios.CidadeRep;
import com.pjt.vendas.repositorios.FuncionarioRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class FuncionarioControle {
    @Autowired
    private FuncionarioRep funcionarioRep;
    @Autowired
    private CidadeRep cidadeRep;

    @GetMapping("/cadastraFuncionario")
    public ModelAndView cadastrar(Funcionario funcionario){
        ModelAndView mv = new ModelAndView("/adm/funcionarios/cadastro");
        mv.addObject("funcionario", funcionario);
        mv.addObject("listaCidade", cidadeRep.findAll());
        return mv;
    }

    @GetMapping("/listaFuncionario")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/funcionarios/lista");
        mv.addObject("listaFuncionario", funcionarioRep.findAll());
        return mv;
    }

    @GetMapping("/editaFuncionario/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRep.findById(id);

        if (funcionario.isPresent()) {
            return cadastrar(funcionario.get());
        } else {
            // Handle the case when the Funcionario is not found
            // Redirect to the list page or show an error message
            ModelAndView mv = new ModelAndView("redirect:/listaFuncionario");
            mv.addObject("errorMessage", "Funcionario não encontrado!");
            return mv;
        }
    }


    @GetMapping("/removeFuncionario/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = funcionarioRep.findById(id);
        funcionarioRep.delete(funcionario.get());
        return listar();
    }

    @PostMapping("/salvaFuncionario")
    public ModelAndView salvar(Funcionario funcionario, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(funcionario);
        }
        funcionarioRep.saveAndFlush(funcionario);
        return cadastrar(new Funcionario());
    }
}
