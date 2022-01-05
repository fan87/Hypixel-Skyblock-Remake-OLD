package me.fan87.commonplugin.utils;

import lombok.Getter;
import org.bukkit.inventory.ShapelessRecipe;

import java.lang.reflect.Array;
import java.util.function.Predicate;

public class SBMatrix<T> {

    @Getter
    private final T[] content;
    private final Class<T> clazz;
    @Getter
    private final int width;
    @Getter
    private final int height;

    public SBMatrix(T[] content, Class<T> clazz, int width, int height) {
        assert content.length == width*height;
        this.content = content;
        this.clazz = clazz;
        this.width = width;
        this.height = height;
    }

    public T[] getRowItems(int rowIndex) {
        T[] row = (T[]) Array.newInstance(clazz, width);
        int index = 0;
        for (int i = (width) * rowIndex; i < width * (rowIndex + 1); i++) {
            row[index++] = content[i];
        }
        return row;
    }


    public T[] getColumnItems(int columnIndex) {
        T[] column = (T[]) Array.newInstance(clazz, height);

        for (int i = 0; i < column.length; i++) {
            column[i] = content[columnIndex + i*width];
        }
        return column;
    }

    public T[] getItems(int x1, int y1, int x2, int y2) {
        int fromX = Math.min(x1, x2) + 1;
        int fromY = Math.min(y1, y2) + 1;
        int toX = Math.max(x1, x2) + 1;
        int toY = Math.max(y1, y2) + 1;
        T[] output = (T[]) Array.newInstance(clazz, (toX - fromX + 1)*(toY - fromY + 1));
        int index = 0;
        for (int i = 0; i < content.length; i++) {
            int x = i % height + 1;
            int y = i / width + 1;
            if (NumberUtils.isBetween(x, fromX, toX) && NumberUtils.isBetween(y, fromY, toY)) {
                output[index++] = content[i];
            }
        }
        return output;
    }

    public T getItem(int x, int y) {
        return content[width*y + x];
    }

    public SBMatrix<T> cleanEmpty(Predicate<T> emptyChecker) {
        if (height == 0 || width == 0) return this;
        for (int r = 0; r < 2; r++) {
            int row = 0;
            if (r == 1) row = height - 1;
            T[] rowItems = getRowItems(row);
            for (int i = 0; i < rowItems.length; i++) {
                if (!emptyChecker.test(rowItems[i])) {
                    break;
                }
                if (i == rowItems.length - 1) {
                    T[] cleaned = (T[]) Array.newInstance(clazz, width * (height-1));
                    int index = 0;
                    for (int y = 0; y < height; y++) {
                        if (y == row) continue;
                        for (int x = 0; x < width; x++) {
                            cleaned[index++] = getItem(x, y);
                        }
                    }
                    return new SBMatrix<T>(cleaned, clazz, width, height - 1).cleanEmpty(emptyChecker);
                }
            }
        }
        for (int r = 0; r < 2; r++) {
            int column = 0;
            if (r == 1) column = width - 1;
            T[] rowItems = getColumnItems(column);
            for (int i = 0; i < rowItems.length; i++) {
                if (!emptyChecker.test(rowItems[i])) break;
                if (i == rowItems.length - 1) {
                    T[] cleaned = (T[]) Array.newInstance(clazz, height * (width-1));
                    int index = 0;
                    for (int y = 0; y < height; y++) {
                        for (int x = 0; x < width; x++) {
                            if (x == column) continue;
                            cleaned[index++] = getItem(x, y);
                        }
                    }
                    return new SBMatrix<T>(cleaned, clazz, width - 1, height).cleanEmpty(emptyChecker);
                }
            }
        }
        return new SBMatrix<>(content, clazz, width, height);
    }

    public int getColumnCount() {
        return height;
    }

    public int getRowCount() {
        return width;
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        for (int y = 0; y < height; y++) {
            out.append("[");
            for (int x = 0; x < width; x++) {
                out.append(content[x + y * width]);
                if (x != width - 1) {
                    out.append(", ");
                }
            }
            out.append("]\n");
        }
        return out.toString();
    }


}
