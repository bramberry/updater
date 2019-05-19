package by.vsu.bramberry.updatechecker.model.entity.hardware;

import lombok.Data;

import java.util.List;

@Data
class NetworkAdapter {
    private String mac;
    private String name;
    private String speed;
    private List<String> ipAdresses;
}
