package com.americinn.timelycheck.mapper;

import com.americinn.timelycheck.entity.Staff;
import com.americinn.timelycheck.model.StaffModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StaffMapper {

    Staff toEntity(StaffModel staffModel);

    // Mapping from Staff to StaffModel
    StaffModel toModel(Staff staff);
}

