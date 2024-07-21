package io.p4r53c.telran.utils;

import static org.junit.jupiter.api.Assertions.*;

import io.p4r53c.telran.utils.transformer.BigCat;
import io.p4r53c.telran.utils.transformer.Cat;
import io.p4r53c.telran.utils.transformer.CatTransformer;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class CatTransformerTest {

    @Test
    void testCatToBigCat() {
        CatTransformer<Cat, BigCat> catToBigCat = cat -> new BigCat(cat.getWeight(), cat.getName(), cat.getKind());

        Cat cat = new Cat(10, "Kitty", "Domestic");
        BigCat bigCat = catToBigCat.verboseTransform(cat);

        assertEquals("Kitty", bigCat.getName());
        assertEquals(10, bigCat.getWeight());
        assertEquals("Domestic", bigCat.getKind());
    }

    @Test
    void testBigCatToCat() {
        CatTransformer<BigCat, Cat> bigCatToCat = bigCat -> new Cat(bigCat.getWeight(), bigCat.getName(),
                bigCat.getKind());

        BigCat bigCat = new BigCat(40, "Leopard", "Wild");
        Cat cat = bigCatToCat.verboseTransform(bigCat);

        assertEquals("Leopard", cat.getName());
        assertEquals(40, cat.getWeight());
        assertEquals("Wild", cat.getKind());
    }

    @Test
    void testTransformAll() {
        CatTransformer<Cat, BigCat> catToBigCat = cat -> new BigCat(cat.getWeight(), cat.getName(), cat.getKind());

        List<Cat> cats = Arrays.asList(new Cat(10, "Kitty", "Domestic"), new Cat(15, "Tom", "Street"));
        
        List<BigCat> bigCats = catToBigCat.transformAll(cats);

        assertEquals(2, bigCats.size());

        assertEquals("Kitty", bigCats.get(0).getName());

        assertEquals(10, bigCats.get(0).getWeight());

        assertEquals("Domestic", bigCats.get(0).getKind());

        assertEquals("Tom", bigCats.get(1).getName());

        assertEquals(15, bigCats.get(1).getWeight());

        assertEquals("Street", bigCats.get(1).getKind());
    }

    @Test
    void testTransformationChain() {
        CatTransformer<Cat, BigCat> catToBigCat = cat -> new BigCat(cat.getWeight(), cat.getName(), cat.getKind());
        CatTransformer<BigCat, Cat> bigCatToCat = bigCat -> new Cat(bigCat.getWeight(), bigCat.getName(),
                bigCat.getKind());

        CatTransformer<Cat, Cat> catToCat = CatTransformer.transformChain(catToBigCat, bigCatToCat);

        Cat originalCat = new Cat(10, "Kitty", "Domestic");
        Cat transformedCat = catToCat.verboseTransform(originalCat);

        assertEquals("Kitty", transformedCat.getName());
        assertEquals(10, transformedCat.getWeight());
        assertEquals("Domestic", transformedCat.getKind());
    }
}