package org.example.pimob.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CreateCorretorExceptioonDto {
  private Integer httpStatus;
  private String message;

}
