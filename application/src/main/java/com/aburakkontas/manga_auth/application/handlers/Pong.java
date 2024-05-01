package com.aburakkontas.manga_auth.application.handlers;

import an.awesome.pipelinr.Command;
import com.aburakkontas.manga_auth.application.commands.Ping;

public class Pong implements Command.Handler<Ping, String> {

    @Override
    public String handle(Ping command) {
        return "Pong from " + command.host();
    }
}