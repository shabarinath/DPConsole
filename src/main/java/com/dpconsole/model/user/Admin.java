package com.dpconsole.model.user;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "admin")
@SuppressWarnings("serial")
public class Admin extends User {

}