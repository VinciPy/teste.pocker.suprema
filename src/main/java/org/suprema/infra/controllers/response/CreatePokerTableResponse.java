package org.suprema.infra.controllers;

public class CreatePokerTableResponse {
    private String tableId;

    public CreatePokerTableResponse(String tableId) {
        this.tableId = tableId;
    }

    public String getTableId() {
        return tableId;
    }

    public void setTableId(String tableId) {
        this.tableId = tableId;
    }
}
