package com.schoolmanagement.payload.request;

import com.schoolmanagement.payload.request.abstracts.BaseUserRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class DeanRequest extends BaseUserRequest {
}
