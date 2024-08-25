package co.thairong.mini_pos.dto;

import lombok.Builder;

@Builder
public record FileDto(String name,
                      String uri,
                      String extension,
                      long size) {
}
