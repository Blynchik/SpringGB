package ru.gb.tasks.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
@Aspect
public class LogAspect {

    private FileWriter fileWriter;

    public LogAspect() throws IOException {
        fileWriter = new FileWriter("log.log");
    }

    @Before("@annotation(ru.gb.tasks.aspect.TrackUserAction)")
    public void logExecutionTime(JoinPoint joinPoint) throws Throwable {
        String log = "Пользователь использует метод " + joinPoint.getSignature();
        fileWriter.write(log + "\n");
        fileWriter.flush();
        System.out.println(log);
    }

    @After("execution(* ru.gb.tasks.TasksApplication.main(..))")
    public void closeFileWriter() throws IOException {
        fileWriter.close();
    }
}
