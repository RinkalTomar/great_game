package com.ongraph.greatsgames.controllers.impl;

import com.ongraph.greatsgames.beans.dto.BrandBean;
import com.ongraph.greatsgames.beans.dto.search.BrandSearchCriteria;
import com.ongraph.greatsgames.beans.response.AbstractResponse;
import com.ongraph.greatsgames.beans.response.AbstractResponseBean;
import com.ongraph.greatsgames.controllers.BrandApi;
import com.ongraph.greatsgames.dao.BrandRepository;
import com.ongraph.greatsgames.entities.Brand;
import com.ongraph.greatsgames.exception.BadRequestException;
import com.ongraph.greatsgames.exception.GreatGamesAuthorizationException;
import com.ongraph.greatsgames.exception.GreatGamesException;
import com.ongraph.greatsgames.services.IBrandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class BrandApiController extends AbstractController implements BrandApi {
    private static final Logger _log= LogManager.getLogger(BrandApiController.class);

    @Autowired
    IBrandService _brandService;
    @Autowired
    BrandRepository _brandRepository;


    @Override
    @PreAuthorize("hasAuthority('BRAND_WRITE')")
    public ResponseEntity<AbstractResponseBean> addOrUpdateBrand(@Valid @RequestBody BrandBean body)throws Exception{
        AbstractResponseBean<Long,BrandBean> responseBean = new AbstractResponseBean<>();
        Long brandID=null;
        boolean isUpdateRequest = false;

        if (null != body.getId())
            isUpdateRequest = true;
        if (!isUpdateRequest) {
                brandID = _brandService.saveBrand(body);
        }
        _log.info("Create new Brand");
        responseBean.setObjectId(brandID);
        responseBean.setSuccess(true);
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);

    }

    @Override
    @PreAuthorize("hasAuthority('BRAND_READ')")
    public ResponseEntity<AbstractResponseBean>getAllBrands(@RequestParam(required = false) Map<String, String> searchParams)throws Exception{
        AbstractResponseBean<Long,BrandBean> responseBean = new AbstractResponseBean<>();
        BrandSearchCriteria searchCriteria = new BrandSearchCriteria();
        parseCommonSearchParams(searchCriteria,searchParams);
        _brandService.getAllBrands(responseBean,searchCriteria);

        _log.info("Get all Brands");
        return new ResponseEntity<AbstractResponseBean>(responseBean, HttpStatus.OK);

    }

    @Override
    @PreAuthorize("hasAuthority('BRAND_READ')")
    public ResponseEntity<AbstractResponseBean>getBrandsById(@PathVariable Long id)throws Exception {
        AbstractResponseBean<Long,BrandBean> responseBean = new AbstractResponseBean<>();
        BrandBean brandBean = _brandService.getBrandsById(id);
        _log.info("Get Brand by id");
        responseBean.setObject(brandBean);
        responseBean.setObjectId(brandBean.getId());
        return new ResponseEntity<AbstractResponseBean>(responseBean,HttpStatus.OK);
    }
}
