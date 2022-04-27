package com.project_serralheria.controllers;

import com.project_serralheria.models.Cliente;
import com.project_serralheria.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/")
@CrossOrigin(value="*")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/")
    public String inicio(){
        return "redirect:/clientes";
    }

    @GetMapping("/cadastrar")
    public String form(){
        return "cadastro/formCliente";
    }

    @PostMapping("/cadastrar")
    public String salvarCliente(@Valid Cliente cliente, BindingResult res, RedirectAttributes attibutes) {
        if(res.hasErrors()){
            attibutes.addFlashAttribute("mensagem", "Verifique os Campos!");
            return "redirect:/cadastrar";
        }
        cliente.setDataCadastro(LocalDate.now());
        clienteService.save(cliente);
        attibutes.addFlashAttribute("mensagem", "Sucesso!");
        return "redirect:/cadastrar";
    }

    @GetMapping("/clientes")
    public ModelAndView listar(){

        ModelAndView mv = new ModelAndView("listar/listarClientes");
        List<Cliente> c = clienteService.findAll();
        mv.addObject("clientes", c);
        return mv;
    }
    @GetMapping("/{id}")
    public ModelAndView details(@PathVariable("id") Long id){
        ModelAndView mv = new ModelAndView("Details/detalhesCliente");
        Cliente cliente = clienteService.findById(id);
        mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/deletar")
    public String delete(Long id){
        clienteService.deleteById(id);
        return "redirect:/clientes";
    }

}
