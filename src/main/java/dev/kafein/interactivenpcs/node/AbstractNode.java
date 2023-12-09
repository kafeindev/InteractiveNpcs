package dev.kafein.interactivenpcs.node;

import com.google.common.collect.Lists;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public abstract class AbstractNode<T> implements Node<T> {
    private final @Nullable Node<T> parent;
    private final List<Node<T>> children;

    private volatile @Nullable Object key;
    private volatile @Nullable T value;

    protected AbstractNode(@Nullable Node<T> parent, List<Node<T>> children) {
        this.parent = parent;
        this.children = children;
    }

    protected AbstractNode(@Nullable Node<T> parent, List<Node<T>> children, @Nullable Object key, @Nullable T value) {
        this.parent = parent;
        this.children = children;
        this.key = key;
        this.value = value;
    }

    @Override
    public @Nullable Object getKey() {
        return this.key;
    }

    @Override
    public @Nullable T getValue() {
        return this.value;
    }

    @Override
    public @Nullable Node<T> getParent() {
        return this.parent;
    }

    @Override
    public List<Node<T>> getChildren() {
        return this.children;
    }

    @Override
    public void setKey(@Nullable Object key) {
        this.key = key;
    }

    @Override
    public void setValue(@Nullable T value) {
        this.value = value;
    }

    @Override
    public Node<T> addChild(@Nullable Node<T> child) {
        this.children.add(child);
        return child;
    }

    @Override
    public @Nullable Node<T> removeChild(@Nullable Node<T> child) {
        this.children.remove(child);
        return child;
    }

    @Override
    public void removeChild(Object key) {
        this.children.removeIf(node -> node.getKey() != null && node.getKey().equals(key));
    }

    @Override
    public void removeChildren() {
        this.children.clear();
    }

    @Override
    public @Nullable Node<T> findChild(Predicate<Node<T>> predicate) {
        for (Node<T> child : this.getChildren()) {
            if (predicate.test(child)) {
                return child;
            }
        }

        return null;
    }

    @Override
    public @Nullable Node<T> findChildByValue(@Nullable T value) {
        return findChild(node -> node.getValue() != null && node.getValue().equals(value));
    }

    @Override
    public @Nullable Node<T> findChildByKey(@Nullable Object key) {
        return findChild(node -> node.getKey() != null && node.getKey().equals(key));
    }

    @Override
    public @Nullable Node<T> findChildByKey(@Nullable Object... keys) {
        @Nullable Node<T> node = this;
        for (Object key : keys) {
            node = node.findChildByKey(key);
        }

        return node;
    }

    @Override
    public @Nullable Node<T> findRoot() {
        Node<T> node = this;
        while (node.getParent() != null) {
            node = node.getParent();
        }

        return node;
    }

    @Override
    public void remove() {
        Node<T> parent = this.getParent();
        if (parent != null) {
            parent.removeChild(this);
        }
    }

    @Override
    public boolean isRoot() {
        return this.getParent() == null;
    }

    @Override
    public boolean isLeaf() {
        return this.getChildren().isEmpty();
    }

    @Override
    public boolean isChild(@Nullable Node<T> node) {
        return this.getChildren().contains(node);
    }

    public abstract static class AbstractBuilder<T, B extends AbstractBuilder<T, B>> implements Builder<T, B> {
        protected @Nullable Object key;
        protected @Nullable T value;
        protected @Nullable Node<T> parent;
        protected @Nullable List<Node<T>> children;

        @SuppressWarnings("unchecked")
        public B self() {
            return (B) this;
        }

        @Override
        public B key(@Nullable Object key) {
            this.key = key;
            return self();
        }

        @Override
        public B value(@Nullable T value) {
            this.value = value;
            return self();
        }

        @Override
        public B parent(@Nullable Node<T> parent) {
            this.parent = parent;
            return self();
        }

        @Override
        public B children(@Nullable List<Node<T>> children) {
            this.children = children;
            return self();
        }

        @Override
        public B child(@Nullable Node<T> child) {
            if (this.children == null) {
                this.children = Lists.newArrayList(child);
            } else {
                this.children.add(child);
            }

            return self();
        }
    }
}
