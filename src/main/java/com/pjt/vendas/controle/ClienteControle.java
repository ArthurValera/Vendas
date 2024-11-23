package com.pjt.vendas.controle;

import com.pjt.vendas.modelos.Cliente;
import com.pjt.vendas.repositorios.CidadeRep;
import com.pjt.vendas.repositorios.ClienteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
public class ClienteControle {
    @Autowired
    private ClienteRep clienteRep;
    @Autowired
    private CidadeRep cidadeRep;

    @GetMapping("/cadastraCliente")
    public ModelAndView cadastrar(Cliente cliente){
        ModelAndView mv = new ModelAndView("/adm/clientes/cadastro");
        mv.addObject("cliente", cliente);
        mv.addObject("listaCidade", cidadeRep.findAll());
        return mv;
    }

    @GetMapping("/listaCliente")
    public ModelAndView listar(){
        ModelAndView mv = new ModelAndView("/adm/clientes/lista");
        mv.addObject("listaCliente", clienteRep.findAll());
        return mv;
    }

    @GetMapping("/editaCliente/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRep.findById(id);

        if (cliente.isPresent()) {
            return cadastrar(cliente.get());
        } else {
            // Handle the case when the Cliente is not found
            // Redirect to the list page or show an error message
            ModelAndView mv = new ModelAndView("redirect:/listaCliente");
            mv.addObject("errorMessage", "Cliente não encontrado!");
            return mv;
        }
    }


    @GetMapping("/removeCliente/{id}")
    public ModelAndView remover(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRep.findById(id);
        clienteRep.delete(cliente.get());
        return listar();
    }

    @PostMapping("/salvaCliente")
    public ModelAndView salvar(Cliente cliente, BindingResult result){
        if(result.hasErrors()){
            return cadastrar(cliente);
        }
        clienteRep.saveAndFlush(cliente);
        return cadastrar(new Cliente());
    }
}
