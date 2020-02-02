package com.ongraph.greatsgames.converters;

import com.ongraph.greatsgames.beans.dto.CasinoBean;
import com.ongraph.greatsgames.entities.Address;
import com.ongraph.greatsgames.entities.Casino;
import com.ongraph.greatsgames.enums.Enumeration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CasinoConverter  extends AbstractAuditableConverter {

    @Autowired
    ClientConverter _clientConverter;

    @Autowired
    AddressConverter _addressConverter;

    @Autowired
    CasinoContactConverter _casinoContactConverter;

    @Autowired
    SlotMachineConverter _slotMachineConverter;

    public Casino getEntityFromBean(CasinoBean bean) {
        Casino entity = new Casino();

        populateAuditableEntityFromBean(bean, entity);

        entity.setClientId(bean.getClientId());
        try {
            entity.setClient(_clientConverter.getEntityFromBean(bean.getClient()));
        } catch (Exception e) {
        }
        try {
            entity.setAddress(_addressConverter.getEntityFromBean(bean.getAddress()));
        } catch (Exception e) {
        }
        entity.setLegalRepresentative(bean.getLegalRepresentative());
        entity.setName(bean.getName());
        entity.setNotraryEstate(bean.getNotraryEstate());
        entity.setPublicDocumentDate(bean.getPublicDocumentDate());
        entity.setPublicDocumentDueDate(bean.getPublicDocumentDueDate());
        entity.setPublicDocumentFileName(bean.getPublicDocumentFileName());
        entity.setPublicDocumentNotary(bean.getPublicDocumentNotary());
        entity.setPublicDocumentRegister(bean.getPublicDocumentRegister());
        entity.setPublicDocumentState(bean.getPublicDocumentState());
        entity.setPublicDocumentUrl(bean.getPublicDocumentUrl());
        entity.setPublicNotaryNumber(bean.getPublicNotaryNumber());
        entity.setStatus(bean.getStatus());

        return entity;

    }

    public List<CasinoBean> getBeansFromEntities(List<Casino> entities,Enumeration.ResultType type) {

        List<CasinoBean> beans = new ArrayList<>();

        for (Casino entity : entities) {
            beans.add(getBeanFromEntityForList(entity,type));
        }
        return beans;
    }

    public CasinoBean getBeanFromEntityForList(Casino entity,Enumeration.ResultType type) {
        if (null != entity) {
            CasinoBean bean = new CasinoBean();

            populateAuditableBeanFromEntity(bean, entity);
            bean.setName(entity.getName());
            if(type == Enumeration.ResultType.LISTING || type == Enumeration.ResultType.FULL){

                bean.setClientId(entity.getClientId());
                bean.setLegalRepresentative(entity.getLegalRepresentative());

                bean.setNotraryEstate(entity.getNotraryEstate());
                bean.setPublicDocumentDate(entity.getPublicDocumentDate());
                bean.setPublicDocumentDueDate(entity.getPublicDocumentDueDate());
                bean.setPublicDocumentFileName(entity.getPublicDocumentFileName());
                bean.setPublicDocumentNotary(entity.getPublicDocumentNotary());
                bean.setPublicDocumentRegister(entity.getPublicDocumentRegister());
                bean.setPublicDocumentState(entity.getPublicDocumentState());
                bean.setPublicDocumentUrl(entity.getPublicDocumentUrl());
                bean.setPublicNotaryNumber(entity.getPublicNotaryNumber());
                bean.setStatus(entity.getStatus());

                if(type == Enumeration.ResultType.FULL){

                    bean.setClient(_clientConverter.getBeanFromEntity(entity.getClient()));
                    bean.setAddress(_addressConverter.getBeanFromEntity(entity.getAddress()));
                    if (entity.getCasinoContacts() != null)
                        bean.setContacts(_casinoContactConverter.getBeansFromEntitiesForContact(entity.getCasinoContacts()));
                    if (entity.getSlotMachines() != null)
                        bean.setSlotMachines(_slotMachineConverter.getBeansFromEntities(entity.getSlotMachines()));
                }

            }
            return bean;

        }
        else
            return null;
    }
    public CasinoBean getBeanFromEntity(Casino entity, Enumeration.ResultType type) {

        if (null != entity) {
            CasinoBean bean = new CasinoBean();

            populateAuditableBeanFromEntity(bean, entity);
            bean.setClientId(entity.getClientId());
            bean.setLegalRepresentative(entity.getLegalRepresentative());
            bean.setName(entity.getName());
            bean.setNotraryEstate(entity.getNotraryEstate());
            bean.setPublicDocumentDate(entity.getPublicDocumentDate());
            bean.setPublicDocumentDueDate(entity.getPublicDocumentDueDate());
            bean.setPublicDocumentFileName(entity.getPublicDocumentFileName());
            bean.setPublicDocumentNotary(entity.getPublicDocumentNotary());
            bean.setPublicDocumentRegister(entity.getPublicDocumentRegister());
            bean.setPublicDocumentState(entity.getPublicDocumentState());
            bean.setPublicDocumentUrl(entity.getPublicDocumentUrl());
            bean.setPublicNotaryNumber(entity.getPublicNotaryNumber());
            bean.setStatus(entity.getStatus());
            try {
                if (type == Enumeration.ResultType.SELECTION || type == Enumeration.ResultType.FULL) {

                    if (entity.getCasinoContacts() != null)
                        bean.setContacts(_casinoContactConverter.getBeansFromEntitiesForContact(entity.getCasinoContacts()));
                    if (entity.getSlotMachines() != null)
                        bean.setSlotMachines(_slotMachineConverter.getBeansFromEntities(entity.getSlotMachines()));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bean;
        } else
            return null;
    }

    public CasinoBean getBeanFromEntityForCasinoContact(Casino entity) {

        if (null != entity) {
            CasinoBean bean = new CasinoBean();

            populateAuditableBeanFromEntity(bean, entity);
            bean.setClientId(entity.getClientId());
            bean.setLegalRepresentative(entity.getLegalRepresentative());
            bean.setName(entity.getName());
            bean.setNotraryEstate(entity.getNotraryEstate());
            bean.setPublicDocumentDate(entity.getPublicDocumentDate());
            bean.setPublicDocumentDueDate(entity.getPublicDocumentDueDate());
            bean.setPublicDocumentFileName(entity.getPublicDocumentFileName());
            bean.setPublicDocumentNotary(entity.getPublicDocumentNotary());
            bean.setPublicDocumentRegister(entity.getPublicDocumentRegister());
            bean.setPublicDocumentState(entity.getPublicDocumentState());
            bean.setPublicDocumentUrl(entity.getPublicDocumentUrl());
            bean.setPublicNotaryNumber(entity.getPublicNotaryNumber());
            bean.setStatus(entity.getStatus());

            return bean;
        } else
            return null;
    }

    public CasinoBean getBeanFromEntityForSlotMachine(Casino entity) {
        if (null != entity) {
            CasinoBean bean = new CasinoBean();

            populateAuditableBeanFromEntity(bean, entity);
            bean.setClientId(entity.getClientId());
            bean.setLegalRepresentative(entity.getLegalRepresentative());
            bean.setName(entity.getName());
            bean.setNotraryEstate(entity.getNotraryEstate());
            bean.setPublicDocumentDate(entity.getPublicDocumentDate());
            bean.setPublicDocumentDueDate(entity.getPublicDocumentDueDate());
            bean.setPublicDocumentFileName(entity.getPublicDocumentFileName());
            bean.setPublicDocumentNotary(entity.getPublicDocumentNotary());
            bean.setPublicDocumentRegister(entity.getPublicDocumentRegister());
            bean.setPublicDocumentState(entity.getPublicDocumentState());
            bean.setPublicDocumentUrl(entity.getPublicDocumentUrl());
            bean.setPublicNotaryNumber(entity.getPublicNotaryNumber());
            bean.setStatus(entity.getStatus());
            return bean;
        }
         else
             return null;
    }
}

