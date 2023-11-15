package ca.robertgleason.database.mappers.impl;

import ca.robertgleason.database.domain.entities.Author;
import ca.robertgleason.database.domain.entities.dto.AuthorDto;
import ca.robertgleason.database.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements Mapper<Author, AuthorDto> {

    private ModelMapper modelMapper;

    public AuthorMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public AuthorDto mapTo(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    @Override
    public Author mapFrom(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }
}
