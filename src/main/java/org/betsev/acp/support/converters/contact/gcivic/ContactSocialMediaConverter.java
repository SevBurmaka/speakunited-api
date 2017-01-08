package org.betsev.acp.support.converters.contact.gcivic;

import org.betsev.acp.support.Constants;
import org.dozer.ConfigurableCustomConverter;

import java.util.List;
import java.util.Map;

/**
 * Created by sevburmaka on 12/7/16.
 */
public class ContactSocialMediaConverter implements ConfigurableCustomConverter{
    String parameter ;
    private final static String TYPE_FIELD = "type";
    private final static String VALUE_FIELD = "id";

    @Override
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    @Override
    public Object convert(Object existingDestinationFieldValue, Object sourceFieldValue, Class<?> destinationClass, Class<?> sourceClass) {
        List<Map<String,String>> channels = (List<Map<String,String>>) sourceFieldValue;

        if (channels != null) {
            for (Map<String, String> channel : channels) {
                if (isCorrectChannel(channel))
                    return channel.get(VALUE_FIELD);
            }
        }
        return null;
    }

    private boolean isCorrectChannel(Map<String,String> channel){
        if (parameter.equals(Constants.FACEBOOK_STRING) && channel.get(TYPE_FIELD).equals(Constants.FACEBOOK_STRING)){
            return true;
        }
        if (parameter.equals(Constants.TWITTER_STRING) && channel.get(TYPE_FIELD).equals(Constants.TWITTER_STRING)){
            return true;
        }
        if (parameter.equals(Constants.YOUTUBE_STRING) && channel.get(TYPE_FIELD).equals(Constants.YOUTUBE_STRING)){
            return true;
        }
        return false;
    }}
