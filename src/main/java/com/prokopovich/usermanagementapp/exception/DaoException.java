package com.prokopovich.usermanagementapp.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DaoException extends RuntimeException {

    private static final Logger LOGGER = LogManager.getLogger(DaoException.class);

    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
        LOGGER.error(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
        LOGGER.error(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
        LOGGER.error(cause);
    }
}