package br.com.alura.mudi.controller;

import br.com.alura.mudi.dto.RequisicaoNovoPedido;
import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.model.User;
import br.com.alura.mudi.repository.PedidoRepository;
import br.com.alura.mudi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("pedido")
public class PedidoController {
    @Autowired
    private PedidoRepository repository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("formulario")
    public String formulario() {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String create(@Valid RequisicaoNovoPedido requisicaoNovoPedido, BindingResult result) {
        if (result.hasErrors()) {
            return "pedido/formulario";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.findByUsername(username);
        Pedido pedido = requisicaoNovoPedido.toPedido();
        pedido.setUser(user);
        pedido.setStatus(StatusPedido.AGUARDANDO);
        repository.save(pedido);
        return "redirect:/home";
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Pedido pedido){
        repository.delete(pedido);
    }

}
