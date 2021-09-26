package br.com.alura.mudi.controller;

import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private PedidoRepository repository;


    @GetMapping("pedido")
    public ModelAndView home(Principal principal) {
        List<Pedido> pedidos = repository.findAllByUser(principal.getName());
        ModelAndView mv = new ModelAndView("usuario/home");
        mv.addObject("pedidos", pedidos);
        return mv;
    }

    @GetMapping("pedido/{status}")
    public ModelAndView porStatus(@PathVariable("status") String status, Principal principal) {
        List<Pedido> pedidos = repository.findByStatusandUser(StatusPedido.valueOf(status.toUpperCase()), principal.getName());
        ModelAndView mv = new ModelAndView("usuario/home");
        mv.addObject("pedidos", pedidos);
        mv.addObject("status", status);
        return mv;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String onError() {
        return "redirect:usuario/home";
    }

}
