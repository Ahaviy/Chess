public enum Color { WHITE("White"), BLACK("Black");
    private String color;

    public String getColor() {
        return color;
    }

    Color(String color) {
        this.color = color;
    }
}
