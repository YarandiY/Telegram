package client.Exception;

import client.controller.Page;

public class EmptyField extends Exception {

    @Override
    public String getMessage() {
        return "please enter your username and pass";
    }
}
