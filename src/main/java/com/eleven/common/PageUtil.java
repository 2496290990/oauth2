package com.eleven.common;

import org.springframework.stereotype.Component;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author zhaojinhui
 * @date 2021/3/20 11:52
 * @apiNote
 */
@Component
public class PageUtil<T> {

    public PageParam<T> getPageInfo(T t,List<T> data){
        PageParam<T> page = new PageParam<>();
        page.setTotal(data.size());
        Class<?> clazz = t.getClass();
        Integer currentPage = 1;
        Integer pageSize = 10;
        Integer fromIndex = 1;
        Integer toIndex = 10;
        try {
            PropertyDescriptor currentPageMethod = new PropertyDescriptor("currentPage", clazz);
            Method getCurrentPage = currentPageMethod.getReadMethod();
            currentPage = (int) getCurrentPage.invoke(t);

            PropertyDescriptor pageSizeMethod = new PropertyDescriptor("pageSize", clazz);
            Method getPageSize = pageSizeMethod.getReadMethod();
            pageSize = (int) getPageSize.invoke(t);
        } catch (IntrospectionException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        fromIndex = (currentPage - 1) * pageSize;
        toIndex = fromIndex + pageSize;
        if (toIndex > data.size()) {
            toIndex = data.size();
        }
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);
        page.setData(data.subList(fromIndex, toIndex));
        return page;
    }

}
