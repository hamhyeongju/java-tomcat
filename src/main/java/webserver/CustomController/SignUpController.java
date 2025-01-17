package webserver.CustomController;

import db.MemoryUserRepository;
import http.request.HttpRequest;
import http.response.HttpResponse;
import model.User;

import java.io.IOException;

import static webserver.constant.Http.INDEX;

public class SignUpController implements Controller {

    private final MemoryUserRepository repository;

    public SignUpController() {
        repository = MemoryUserRepository.getInstance();
    }

    @Override
    public void process(HttpRequest request, HttpResponse response) throws IOException {
        User user = new User(
                request.getParamValue("userId"),
                request.getParamValue("password"),
                request.getParamValue("name"),
                request.getParamValue("email"));

        repository.addUser(user);

        response.redirect(INDEX.getValue());
    }
}
