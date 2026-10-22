package com.qsteam.qslib.api.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilities for creating SLF4J loggers with hierarchical names such as {@code root/name}.
 * <p>
 * An implementation only has to provide {@link #getRootName()}. Everything else is built on
 * three extension points that can be overridden:
 * <ul>
 *   <li>{@link #getRootName()} - the root name (usually the mod name); required;</li>
 *   <li>{@link #getSeparator()} - the separator between name parts; {@code "/"} by default;</li>
 *   <li>{@link #getLogger(String)} - logger creation; SLF4J {@link LoggerFactory} by default.</li>
 * </ul>
 * All other methods delegate to {@link #getSeparator()} and {@link #getLogger(String)},
 * so overriding either of them affects every derived logger.
 * <p>
 * The interface holds no global state: each mod uses its own instance, so several mods
 * with different root names can coexist in one JVM. Since it has a single abstract method,
 * a lambda is enough for the simple case:
 * <pre>{@code LogUtils logs = () -> "MyMod";}</pre>
 */
@FunctionalInterface
public interface LogUtils {

    // ---------------- extension points ----------------

    /**
     * Returns the root name shared by all loggers of this instance, usually the mod name.
     *
     * @return the root name, never {@code null}
     */
    String getRootName();

    /**
     * Returns the separator inserted between name parts.
     *
     * @return the separator, {@code "/"} by default
     */
    default String getSeparator() {
        return "/";
    }

    /**
     * Creates (or looks up) a logger by its full name.
     * The default implementation uses {@link LoggerFactory#getLogger(String)}.
     *
     * @param name the full logger name
     * @return the logger
     */
    default Logger getLogger(String name) {
        return LoggerFactory.getLogger(name);
    }

    // ---------------- Mod / Spec ----------------

    /**
     * Returns the root logger, named exactly {@link #getRootName()}.
     *
     * @return the root logger
     */
    default Logger getModLogger() {
        return getLogger(getRootName());
    }

    /**
     * Returns a logger named {@code root + separator + clazz.getSimpleName()}.
     *
     * @param clazz the class whose simple name is used
     * @return the logger
     */
    default Logger getSpecLogger(Class<?> clazz) {
        return getSpecLogger(clazz.getSimpleName());
    }

    /**
     * Returns a logger named {@code root + separator + name}.
     *
     * @param name the name appended to the root name
     * @return the logger
     */
    default Logger getSpecLogger(String name) {
        return getLogger(getRootName(), name);
    }

    // ---------------- Sub ----------------

    /**
     * Returns a child of the given logger, named
     * {@code logger.getName() + separator + clazz.getSimpleName()}.
     *
     * @param logger the parent logger
     * @param clazz  the class whose simple name is used
     * @return the child logger
     */
    default Logger getSubLogger(Logger logger, Class<?> clazz) {
        return getSubLogger(logger, clazz.getSimpleName());
    }

    /**
     * Returns a child of the given logger, named {@code logger.getName() + separator + name}.
     *
     * @param logger the parent logger
     * @param name   the name appended to the parent name
     * @return the child logger
     */
    default Logger getSubLogger(Logger logger, String name) {
        return getLogger(logger.getName(), name);
    }

    // ---------------- composite names ----------------
    // Each overload joins two parts with getSeparator(); a Class part contributes its simple name.

    /**
     * Returns a logger named {@code name + separator + clazz.getSimpleName()}.
     *
     * @param name  the first name part
     * @param clazz the class providing the second part
     * @return the logger
     */
    default Logger getLogger(String name, Class<?> clazz) {
        return getLogger(name, clazz.getSimpleName());
    }

    /**
     * Returns a logger named {@code clazz.getSimpleName() + separator + name}.
     *
     * @param clazz the class providing the first part
     * @param name  the second name part
     * @return the logger
     */
    default Logger getLogger(Class<?> clazz, String name) {
        return getLogger(clazz.getSimpleName(), name);
    }

    /**
     * Returns a logger named {@code clazz.getSimpleName() + separator + clazz2.getSimpleName()}.
     *
     * @param clazz  the class providing the first part
     * @param clazz2 the class providing the second part
     * @return the logger
     */
    default Logger getLogger(Class<?> clazz, Class<?> clazz2) {
        return getLogger(clazz.getSimpleName(), clazz2.getSimpleName());
    }

    /**
     * Returns a logger named {@code name1 + separator + name2}.
     * This is the base overload all composite methods end up in.
     *
     * @param name1 the first name part
     * @param name2 the second name part
     * @return the logger
     */
    default Logger getLogger(String name1, String name2) {
        return getLogger(name1 + getSeparator() + name2);
    }
}
