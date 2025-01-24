package com.nhnacademy.student.common.annotation;

import com.nhnacademy.student.controller.Command;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

@Slf4j
public class StopWatchProxy implements Command {

    private final Command command;

    public StopWatchProxy (Command command){
        this.command = command;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StopWatch stopWatch = command.getClass().getAnnotation(StopWatch.class);
        if(Objects.isNull(stopWatch)){
            return command.execute(req, resp);
        }
        long start = System.currentTimeMillis();

        String view = command.execute(req, resp);

        long end = System.currentTimeMillis();

        log.debug("runtime: {}", end - start);

        return view;
    }
}
