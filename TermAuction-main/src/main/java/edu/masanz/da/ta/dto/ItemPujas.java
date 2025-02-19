package edu.masanz.da.ta.dto;

import java.util.List;

public class ItemPujas {

    private Item item;

    private List<Puja> pujas;

    // region Constructors
    public ItemPujas(Item item, List<Puja> pujas) {
        this.item = item;
        this.pujas = pujas;
    }

    public ItemPujas(Item item) {
        this(item, null);
    }

    public ItemPujas() {
        this(null, null);
    }
    // endregion

    // region Getters and Setters
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public List<Puja> getBids() {
        return pujas;
    }

    public void setBids(List<Puja> pujas) {
        this.pujas = pujas;
    }
    // endregion

    public String getInfo() {
        String s0 = String.format("|%s|", "-".repeat(44));
        String s1 = String.format("| %-42s |", "No tiene pujas.");
        StringBuilder sb = new StringBuilder();
        sb.append(s0).append("\n");
        sb.append(item.getInfo(false)).append("\n");
        if (pujas == null) {
            sb.append(s1).append("\n");
            sb.append(s0).append("\n");
            return sb.toString();
        }
        for (Puja puja : pujas) {
            sb.append(puja.getInfo()).append("\n");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(item.toString()).append("\n");
        if (pujas == null) {
            sb.append("No tiene pujas.\n");
            return sb.toString();
        }
        for (Puja puja : pujas) {
            sb.append(puja.toString()).append("\n");
        }
        return sb.toString();
    }
}
