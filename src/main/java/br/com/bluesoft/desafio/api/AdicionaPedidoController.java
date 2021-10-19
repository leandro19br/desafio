package br.com.bluesoft.desafio.api;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.requests.ProdutoDTO;
import br.com.bluesoft.desafio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*pedidos/novo*/

@RestController
@RequestMapping("/api/pedidos")
public class AdicionaPedidoController {

    private PedidoService service;

    @Autowired
    public AdicionaPedidoController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Iterable<Pedido>> listAll() {
        return ResponseEntity.ok(service.liatAll());
    }

    @PostMapping(path = "/novo")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Pedido> novo(@RequestBody List<ProdutoDTO> produtosDTO) {
        return new ResponseEntity<Pedido>(service.novo(produtosDTO), HttpStatus.CREATED);
    }

}
