package com.path.imco.vo.newapi;

import java.util.ArrayList;
import java.util.List;

import com.path.dbmaps.vo.IM_API_ARGUMENTVO;
import com.path.dbmaps.vo.IM_API_MACHINEVO;
import com.path.dbmaps.vo.IM_API_USERSVO;
import com.path.dbmaps.vo.IM_IMAL_APIVO;
import com.path.lib.vo.BaseVO;

/**
 *
 * Copyright 2013, Path Solutions Path Solutions retains all ownership rights to
 * this source code
 *
 * NewApiCO.java used to
 */
public class NewApiCO extends BaseVO
{
    private IM_IMAL_APIVO imImalApiVO;

    private String UpdateMode;
    private String userGrid;
    private List<NewApiCO> usersList = new ArrayList<>();
    private IM_API_USERSVO imApiUsersVO = new IM_API_USERSVO();
    private List<NewApiCO> machineList = new ArrayList<>();
    private IM_API_MACHINEVO imApiMachineVO = new IM_API_MACHINEVO();
    private List<NewApiCO> argumentsList = new ArrayList<>();
    private IM_API_ARGUMENTVO imApiArgumentVO = new IM_API_ARGUMENTVO();
    private String UserID;
    private String machineGrid;
    private String MachineID;
    private String argumentsGrid;
    private String ArgumentsID;
    private String schemaName;

    public IM_IMAL_APIVO getImImalApiVO()
    {
	return imImalApiVO;
    }

    public void setImImalApiVO(IM_IMAL_APIVO imImalApiVO)
    {
	this.imImalApiVO = imImalApiVO;
    }

    public String getUpdateMode()
    {
	return UpdateMode;
    }

    public void setUpdateMode(String updateMode)
    {
	UpdateMode = updateMode;
    }

    public String getUserID()
    {
	return UserID;
    }

    public void setUserID(String userID)
    {
	UserID = userID;
    }

    public String getUserGrid()
    {
	return userGrid;
    }

    public void setUserGrid(String userGrid)
    {
	this.userGrid = userGrid;
    }

    /**
     * @return the usersList
     */
    public List<NewApiCO> getUsersList()
    {
	return usersList;
    }

    /**
     * @param usersList the usersList to set
     */
    public void setUsersList(List<NewApiCO> usersList)
    {
	this.usersList = usersList;
    }

    /**
     * @return the imApiUsersVO
     */
    public IM_API_USERSVO getImApiUsersVO()
    {
	return imApiUsersVO;
    }

    /**
     * @param imApiUsersVO the imApiUsersVO to set
     */
    public void setImApiUsersVO(IM_API_USERSVO imApiUsersVO)
    {
	this.imApiUsersVO = imApiUsersVO;
    }

    public List<NewApiCO> getMachineList()
    {
	return machineList;
    }

    public void setMachineList(List<NewApiCO> machineList)
    {
	this.machineList = machineList;
    }

    public IM_API_MACHINEVO getImApiMachineVO()
    {
	return imApiMachineVO;
    }

    public void setImApiMachineVO(IM_API_MACHINEVO imApiMachineVO)
    {
	this.imApiMachineVO = imApiMachineVO;
    }

    public String getMachineID()
    {
	return MachineID;
    }

    public void setMachineID(String machineID)
    {
	MachineID = machineID;
    }

    public String getMachineGrid()
    {
	return machineGrid;
    }

    public void setMachineGrid(String machineGrid)
    {
	this.machineGrid = machineGrid;
    }

    public String getArgumentsGrid()
    {
	return argumentsGrid;
    }

    public void setArgumentsGrid(String argumentsGrid)
    {
	this.argumentsGrid = argumentsGrid;
    }

    public String getArgumentsID()
    {
	return ArgumentsID;
    }

    public void setArgumentsID(String argumentsID)
    {
	ArgumentsID = argumentsID;
    }

    public List<NewApiCO> getArgumentsList()
    {
	return argumentsList;
    }

    public void setArgumentsList(List<NewApiCO> argumentsList)
    {
	this.argumentsList = argumentsList;
    }

    public IM_API_ARGUMENTVO getImApiArgumentVO()
    {
	return imApiArgumentVO;
    }

    public void setImApiArgumentVO(IM_API_ARGUMENTVO imApiArgumentVO)
    {
	this.imApiArgumentVO = imApiArgumentVO;
    }

    /**
     * @return the schemaName
     */
	public String getSchemaName() {
		return schemaName;
	}

	/**
     * @param schemaName the schemaName to set
     */
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

}