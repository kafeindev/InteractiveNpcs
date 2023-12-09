package dev.kafein.interactivenpcs.node;

import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public interface Node<T> {
    @Nullable Object getKey();

    @Nullable T getValue();

    @Nullable Node<T> getParent();

    List<Node<T>> getChildren();

    void setKey(@Nullable Object key);

    void setValue(@Nullable T value);

    Node<T> addChild(@Nullable Node<T> child);

    @Nullable Node<T> removeChild(@Nullable Node<T> child);

    void removeChild(Object key);

    void removeChildren();

    @Nullable Node<T> findChild(Predicate<Node<T>> predicate);

    @Nullable Node<T> findChildByValue(@Nullable T value);

    @Nullable Node<T> findChildByKey(@Nullable Object key);

    @Nullable Node<T> findChildByKey(@Nullable Object... keys);

    @Nullable Node<T> findRoot();

    void remove();

    boolean isRoot();

    boolean isLeaf();

    boolean isChild(@Nullable Node<T> node);

    interface Builder<T, B extends Builder<T, B>> {
        B key(@Nullable Object key);

        B value(@Nullable T value);

        B parent(@Nullable Node<T> parent);

        B children(@Nullable List<Node<T>> children);

        B child(@Nullable Node<T> child);

        Node<T> build();
    }
}
