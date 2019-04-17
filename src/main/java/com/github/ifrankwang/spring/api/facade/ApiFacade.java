package com.github.ifrankwang.spring.api.facade;

import com.github.ifrankwang.spring.exception.InvalidRequestArgumentsException;

/**
 * @author Frank Wang
 */
public interface ApiFacade {

    void updateApis() throws InvalidRequestArgumentsException;
}
