package ru.netology.manager;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);
    private Product first = new Book(1, "Lord of the Ring", 500, "Tolkien");
    private Product second = new Book(2, "Harry Potter", 350, "Rowling");
    private Product third  = new Smartphone(3, "Z1", 11000, "Asus");
    private Product forth  = new Smartphone(4, "Galaxy", 20000, "Samsung");


    @BeforeEach
    public void SetUp() {
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
    }

    @Test
    public void getAllProduct() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, forth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchWhenProductMissing() {
        Product[] actual = manager.searchBy("Товар отсутствует");
        Product[] expected = new Product[]{};

        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBookByAuthorName() {
        Product[] actual = manager.searchBy("Tolkien");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchBookByName() {
        Product[] actual = manager.searchBy("Harry Potter");
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchSmartphoneByName() {
        Product[] actual = manager.searchBy("Z1");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void searchSmartphoneByManufacturer() {
        Product[] actual = manager.searchBy("Samsung");
        Product[] expected = new Product[]{forth};
        assertArrayEquals(expected, actual);
    }

}