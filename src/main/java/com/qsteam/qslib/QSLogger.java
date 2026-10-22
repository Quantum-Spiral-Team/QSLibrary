package com.qsteam.qslib;

import com.qsteam.qslib.api.log.LogUtils;
import mezz.jei.util.FieldsAreNonnullByDefault;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Function;

/**
 * Default {@link LogUtils} implementation for QSLib; use {@link #INSTANCE} for the shared one.
 */
@FieldsAreNonnullByDefault
public final class QSLogger implements LogUtils {

    /** Shared instance with the {@code "TM Quantum Spiral"} root name. */
    public static final QSLogger INSTANCE = new QSLogger("TM Quantum Spiral");

    public static final Logger LOGGER = QSLogger.modLogger();

    private final String rootName;
    private final String separator;
    private final Function<String, Logger> loggerFactory;

    /** Creates an instance with the {@code "/"} separator and the SLF4J logger factory. */
    public QSLogger(String rootName) {
        this(rootName, "/", LoggerFactory::getLogger);
    }

    /** Creates an instance with a custom separator and logger factory. */
    public QSLogger(String rootName, String separator, Function<String, Logger> loggerFactory) {
        this.rootName = rootName;
        this.separator = separator;
        this.loggerFactory = loggerFactory;
    }

    @Override
    public String getRootName() {
        return rootName;
    }

    @Override
    public String getSeparator() {
        return separator;
    }

    @Override
    public Logger getLogger(String name) {
        return loggerFactory.apply(name);
    }

    // ---------------- static shortcuts via INSTANCE ----------------

    /** Static shortcut for {@link LogUtils#getModLogger()} on {@link #INSTANCE}. */
    public static Logger modLogger() {
        return INSTANCE.getModLogger();
    }

    /** Static shortcut for {@link LogUtils#getSpecLogger(Class)} on {@link #INSTANCE}. */
    public static Logger specLogger(Class<?> clazz) {
        return INSTANCE.getSpecLogger(clazz);
    }

    /** Static shortcut for {@link LogUtils#getSpecLogger(String)} on {@link #INSTANCE}. */
    public static Logger specLogger(String name) {
        return INSTANCE.getSpecLogger(name);
    }

    /** Static shortcut for {@link LogUtils#getSubLogger(Logger, Class)} on {@link #INSTANCE}. */
    public static Logger subLogger(Logger logger, Class<?> clazz) {
        return INSTANCE.getSubLogger(logger, clazz);
    }

    /** Static shortcut for {@link LogUtils#getSubLogger(Logger, String)} on {@link #INSTANCE}. */
    public static Logger subLogger(Logger logger, String name) {
        return INSTANCE.getSubLogger(logger, name);
    }

    /** Static shortcut for {@link LogUtils#getLogger(String)} on {@link #INSTANCE}. */
    public static Logger logger(String name) {
        return INSTANCE.getLogger(name);
    }

    /** Static shortcut for {@link LogUtils#getLogger(String, Class)} on {@link #INSTANCE}. */
    public static Logger logger(String name, Class<?> clazz) {
        return INSTANCE.getLogger(name, clazz);
    }

    /** Static shortcut for {@link LogUtils#getLogger(Class, String)} on {@link #INSTANCE}. */
    public static Logger logger(Class<?> clazz, String name) {
        return INSTANCE.getLogger(clazz, name);
    }

    /** Static shortcut for {@link LogUtils#getLogger(Class, Class)} on {@link #INSTANCE}. */
    public static Logger logger(Class<?> clazz, Class<?> clazz2) {
        return INSTANCE.getLogger(clazz, clazz2);
    }

    /** Static shortcut for {@link LogUtils#getLogger(String, String)} on {@link #INSTANCE}. */
    public static Logger logger(String name1, String name2) {
        return INSTANCE.getLogger(name1, name2);
    }
}
