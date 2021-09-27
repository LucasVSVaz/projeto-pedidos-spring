package br.com.alura.mudi.controller;

import br.com.alura.mudi.model.Pedido;
import br.com.alura.mudi.model.StatusPedido;
import br.com.alura.mudi.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    @Autowired
    private PedidoRepository repository;

    Sort sort = Sort.by("dataDeEntrega").descending();
    PageRequest pageRequest = PageRequest.of(0, 10, sort);

    @GetMapping
    public ModelAndView home(Principal principal) {
        List<Pedido> pedidos = repository.findByStatus(StatusPedido.ENTREGUE, pageRequest);
        ModelAndView mv = new ModelAndView("/home");
        mv.addObject("pedidos", pedidos);
        return mv;
    }
}
