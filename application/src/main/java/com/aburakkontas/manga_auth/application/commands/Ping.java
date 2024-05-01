package com.aburakkontas.manga_auth.application.commands;

import an.awesome.pipelinr.Command;

public record Ping(String host) implements Command<String> {

}