package com.dlwhi.config;

import java.util.NoSuchElementException;
import java.util.Scanner;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

public class ConfigValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        try (Scanner parser = new Scanner(value).useDelimiter("=")) {
            String[] pv = {parser.next(), parser.next()};
            if ("key".equals(name) && pv[0].length() != 1) {
                throw new ParameterException("Invalid bind: " + name);
            } else if ("char".equals(name) && pv[1].length() != 1) {
                throw new ParameterException("Invalid entity char: " + name);
            }
        } catch (NoSuchElementException e) {
            throw new ParameterException("Invalid config format");
        }
    }    
}
