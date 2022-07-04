public enum ColorPiece { WHITE("White"), BLACK("Black");
    private String color;

    public String getColor() {
        return color;
    }

    ColorPiece(String color) {
        this.color = color;
    }
}
