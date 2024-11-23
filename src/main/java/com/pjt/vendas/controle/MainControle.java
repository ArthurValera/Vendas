package com.pjt.vendas.controle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainControle {
        @GetMapping("/adm")
        public String acessoPrincipal(){
            return "adm/home";
        }
}
