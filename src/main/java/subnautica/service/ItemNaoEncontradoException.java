package subnautica.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ItemNaoEncontradoException extends RuntimeException {
    public ItemNaoEncontradoException(Long id) { super("Item " + id + " não encontrado."); }
}
