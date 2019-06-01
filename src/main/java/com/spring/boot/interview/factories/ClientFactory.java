package com.spring.boot.interview.factories;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import com.spring.boot.interview.dtos.ClientDTO;
import com.spring.boot.interview.enums.SexoEnum;
import com.spring.boot.interview.models.ClientModel;

@Service
public class ClientFactory {

	private ModelMapper modelMapper = new ModelMapper();

	public ClientDTO entityToDto(ClientModel clientModel) {
		return modelMapper.map(clientModel, ClientDTO.class);
	}

	public ClientModel dtoToEntity(ClientDTO clientDto) {
		return modelMapper.map(clientDto, ClientModel.class);
	}

	public List<ClientDTO> listEntityToListDto(List<ClientModel> listClientsModel) {
		java.lang.reflect.Type exit = new TypeToken<List<ClientDTO>>() {
		}.getType();
		modelMapper.addConverter(dateToString);
		modelMapper.addConverter(sexoEnumToString);
		return modelMapper.map(listClientsModel, exit);
	}

	public List<ClientModel> lisDtoToListEntity(List<ClientDTO> listClientsDto) {
		List<ClientModel> exit = new ArrayList<ClientModel>();
		modelMapper.map(listClientsDto, exit);
		return exit;
	}

	private Converter<Date, String> dateToString = new AbstractConverter<Date, String>() {
		protected String convert(Date source) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(source);
		}
	};

	private Converter<SexoEnum, String> sexoEnumToString = new AbstractConverter<SexoEnum, String>() {
		protected String convert(SexoEnum source) {
			switch (source) {
			case MASCULINO:
				return SexoEnum.MASCULINO.getDescricao();
			case FEMININO:
				SexoEnum.FEMININO.getDescricao();
			default:
				return "";
			}
		}
	};
}