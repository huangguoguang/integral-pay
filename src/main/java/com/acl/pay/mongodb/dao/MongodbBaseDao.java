package com.acl.pay.mongodb.dao;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author: chenwei @version：1.0
 * @创建时间：2016年7月25日 下午2:29:58
 * 
 */
@Component
public class MongodbBaseDao<T> {

	@Resource
	private MongoTemplate mongoTemplate;

	/**
	 * 根据ID删除某个集合中的一条记录
	 * 
	 * @param value
	 * @return
	 */
	public void removeById(String value, String collction) {
		Criteria channleIdCri = new Criteria();
		channleIdCri.andOperator(Criteria.where("_id").is(value));
		mongoTemplate.remove(new Query(channleIdCri), collction);
	}

	/**
	 * 根据_id和集合来查询数据
	 * 
	 * @param id
	 * @param collectionName
	 *          集合名
	 * @return
	 */
	public <T> T findById(String id, String collectionName, Class<T> cs) {
		return mongoTemplate.findById(id, cs, collectionName);
	}

	/**
	 * 通过ID获取记录,如果不指定集合名 那么默认在第一个集合中查找
	 * 
	 * @param id
	 * @return
	 */
	public <T> T findById(String id, Class<T> cs) {
		return mongoTemplate.findById(id, cs);
	}

	/**
	 * 通过条件查询更新数据
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public void updateFirst(Query query, Update update, Class<?> entityClass) {
		mongoTemplate.updateFirst(query, update, entityClass);
	}

	/**
	 * 通过ID查询更新数据
	 * 
	 * @param value
	 * @param update
	 * @return
	 */
	public void updateById(String value, Update update, String collection) {
		Query query = Query.query(Criteria.where("_id").is(value));
		mongoTemplate.updateFirst(query, update, collection);
	}

	/**
	 * 查询并且修改记录
	 * 
	 * @param query
	 * @param update
	 * @return
	 */
	public T findAndModify(Query query, Update update, Class<T> cs) {
		return mongoTemplate.findAndModify(query, update, cs);
	}

	/**
	 * 条件查询不分页
	 * 
	 * @param query
	 */
	public List<T> find(Query query, Class<T> cs, String collection) {
		return mongoTemplate.find(query, cs, collection);
	}

	/**
	 * 条件查询
	 * 
	 * @return
	 */
	public List<T> find(String key, String value, Class<T> cs, String collection) {
		Query query = Query.query(Criteria.where(key).is(value));
		return mongoTemplate.find(query, cs, collection);
	}

	/**
	 * 条件查询
	 * 
	 * @return
	 */
	public List<T> find(Map<String, Object> map, Class<T> cs, String collection) {
		Criteria criteria = new Criteria();
		for (String key : map.keySet()) {
			criteria.and(key).is(map.get(key));
		}

		Query query = Query.query(criteria);
		return mongoTemplate.find(query, cs, collection);
	}

	/**
	 * 通过ID查询更新数据
	 * 
	 * @param key
	 * @param update
	 * @return
	 */
	public void update(String key, String value, Update update, String collection) {
		Criteria channleIdCri = new Criteria();
		channleIdCri.andOperator(Criteria.where(key).is(value));
		mongoTemplate.updateFirst(new Query(channleIdCri), update, collection);
	}

}
