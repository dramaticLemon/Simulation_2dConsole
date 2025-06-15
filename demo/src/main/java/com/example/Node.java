package com.example;

public class Node {
    private final int x;
    private final int y;
    private Entity link;
    private TypeObject type;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.type = TypeObject.EMPTY;
    }

    public void setType(TypeObject type) {
        this.type = type;
    }

    public TypeObject getType() {
        return this.type;
    }
    
    public Entity getObjectLink() {
        return this.link;
    }
    public void setObjectLink(Entity newObjectLink) {
        this.link = newObjectLink;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node node = (Node) obj;
        return  x == node.x && y == node.y && type == node.type;
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Entity getLink() {
        return link;
    }
    

}
