package com.jfalbo.japifilter.specifications.builder;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
public class JApiSpecificationBuilderTest {

    @Test
    public void testListWithOneFilter() throws Exception {
        Integer count = JApiSpecificationBuilder.init().withEqualFilter("teste", true).buildList().size();
        assertThat(count, equalTo(1));
    }

    @Test
    public void testListWithTwoFilter() throws Exception {
        Integer count = JApiSpecificationBuilder.init()
                .withEqualFilter("teste", true)
                .withEqualFilter("teste2", "teste")
                .buildList().size();
        assertThat(count, equalTo(2));
    }

    @Test
    public void testListWithTwoFilterButOneValueIsNull() throws Exception {
        Integer count = JApiSpecificationBuilder.init()
                .withEqualFilter("teste", true)
                .withEqualFilter("teste2", null)
                .buildList().size();
        assertThat(count, equalTo(1));
    }

    @Test
    public void testListWithThreeFilterButOneValueIsNull() throws Exception {
        Integer count = JApiSpecificationBuilder.init()
                .withEqualFilter("teste", true)
                .withEqualFilter("teste2", null)
                .withEqualFilter("teste3", "testando")
                .buildList().size();
        assertThat(count, equalTo(2));
    }

    @Test
    public void testSpecificationNotNull() throws Exception {
        Specification<Object> objectSpecification = JApiSpecificationBuilder.init()
                .withEqualFilter("teste", true)
                .withEqualFilter("teste2", "teste")
                .buildSpec();
        assertNotNull(objectSpecification);
    }

}
