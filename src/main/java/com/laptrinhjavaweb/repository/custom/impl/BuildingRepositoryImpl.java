package com.laptrinhjavaweb.repository.custom.impl;

import java.lang.reflect.Field;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dto.BuildingSearchBuilder;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.repository.custom.BuildingRepositoryCustom;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BuildingEntity> getBuildings(BuildingSearchBuilder buildingSearchBuilder) {
		StringBuilder sql = new StringBuilder("SELECT b.* FROM building b");
		if( buildingSearchBuilder.getStaffId() != null) {
			sql.append(" INNER JOIN assignmentbuilding ab ON b.id=ab.buildingid");
		}
		sql.append(" WHERE 1=1");
		sql=buildCommonSql(sql,buildingSearchBuilder);
		sql=buildSpecialSql(sql,buildingSearchBuilder);
		sql.append(";");
		
		Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
		List<BuildingEntity> result = query.getResultList();
		//List<RentAreaEntity> test =  result.get(0).getRentAreas();
		return result;
	}
	private StringBuilder buildCommonSql(StringBuilder sql,BuildingSearchBuilder buildingSearchBuilder) {
		try {
			Field[] listField = BuildingSearchBuilder.class.getDeclaredFields();
			String type;
			for(Field field: listField ) {
				if(!field.getName().equals("staffId")
					&& !field.getName().equals("managerPhone")
					&& !field.getName().equals("managerName")
					&& !field.getName().startsWith("rent")
					&& !field.getName().equals("buildingTypes")
					){
					field.setAccessible(true);
					type=field.getType().getName();
					if(type.equals("java.lang.String")) {
						field.setAccessible(true); // để truy cập giá trị của những field private
						if(field.get(buildingSearchBuilder) != null) {
							sql.append(" AND "+field.getName().toLowerCase()+" like '%"+(String) field.get(buildingSearchBuilder)+"%'");
						}
					}else if(type.equals("java.lang.Integer")) {
						field.setAccessible(true);
						if(field.get(buildingSearchBuilder) != null) {
							sql.append(" AND "+field.getName().toLowerCase()+" = "+(String) field.get(buildingSearchBuilder));
						}
					}
					
				}
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}	
		return sql;
	}
	private StringBuilder buildSpecialSql(StringBuilder sql,BuildingSearchBuilder buildingSearchBuilder) {
		
		// xử lý types
		if(buildingSearchBuilder.getBuildingTypes() != null) {
			sql.append(" AND ( 1=0 ");
			String[] types = buildingSearchBuilder.getBuildingTypes();
			for(String type : types) {
				sql.append(" OR b.buildingtype LIKE '%"+type.toUpperCase()+"%'");
			}
			sql.append(" )");
		}
		
		// xử lý phần giá thuê
		if(buildingSearchBuilder.getRentPriceFrom() != null) {
			sql.append(" AND "+buildingSearchBuilder.getRentPriceFrom()+" <= b.rentprice");
		}
		if(buildingSearchBuilder.getRentPriceTo() != null) {
			sql.append(" AND "+buildingSearchBuilder.getRentPriceTo()+" >= b.rentprice");
		}
		
		// xử lý phần diện tích
		if(buildingSearchBuilder.getRentAreaFrom() != null || buildingSearchBuilder.getRentAreaTo() != null) {
			sql.append(" AND EXISTS (SELECT * FROM rentarea r WHERE r.buildingid = b.id");
			if(buildingSearchBuilder.getRentAreaFrom() != null) {
				sql.append(" AND "+buildingSearchBuilder.getRentAreaFrom()+" <= r.value");
			}
			if(buildingSearchBuilder.getRentAreaTo() != null) {
				sql.append(" AND "+buildingSearchBuilder.getRentAreaTo()+" >= r.value");
			}
			sql.append(" )");
		}
		// xử lý phần nhân viên 
		if(buildingSearchBuilder.getStaffId() != null) {
			sql.append(" AND ab.staffid LIKE '%"+buildingSearchBuilder.getStaffId()+"%'");
		}
		
		return sql;
	}
	
}
