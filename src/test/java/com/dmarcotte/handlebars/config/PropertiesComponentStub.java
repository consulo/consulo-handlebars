package com.dmarcotte.handlebars.config;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.jetbrains.annotations.NonNls;
import com.intellij.ide.util.PropertiesComponent;

public class PropertiesComponentStub implements PropertiesComponent
{
	private final Map<String, String> fakeStorage = new HashMap<String, String>();

	@Override
	public void unsetValue(String name)
	{
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isValueSet(String name)
	{
		return fakeStorage.containsKey(name);
	}

	@Override
	public String getValue(@NonNls String name)
	{
		return fakeStorage.get(name);
	}

	@Override
	public void setValue(@NonNls String name, String value)
	{
		fakeStorage.put(name, value);
	}

	@Override
	public void setValue(@Nonnull String s, @Nullable String s1, @Nullable String s2)
	{

	}

	@Override
	public void setValue(@Nonnull String s, float v, float v1)
	{

	}

	@Override
	public void setValue(@Nonnull String s, int i, int i1)
	{

	}

	@Override
	public void setValue(@Nonnull String s, boolean b, boolean b1)
	{

	}

	@Nonnull
	@Override
	public String getValue(@NonNls String name, @Nonnull String defaultValue)
	{
		throw new UnsupportedOperationException("Implement me if needed");
	}

	@SuppressWarnings("UnusedDeclaration") // required by IDEA 12, but unused when building against IDEA 11
	public String[] getValues(@NonNls String name)
	{
		throw new UnsupportedOperationException("Implement me if needed");
	}

	@SuppressWarnings("UnusedDeclaration") // required by IDEA 12, but unused when building against IDEA 11
	public void setValues(@NonNls String name, String[] values)
	{
		throw new UnsupportedOperationException("Implement me if needed");
	}

	@SuppressWarnings("EmptyMethod") // see comment in method for why this is cool
	@Override
	public String getOrInit(@NonNls String name, String defaultValue)
	{
		// parent is implemented using isValueSet and getValue, so use that to keep things
		// true to form.  There is a tiny chance that will change and this test will start behaving odd...
		// hopefully if that happens, this comment helps resolve the issue
		return PropertiesComponent.super.getOrInit(name, defaultValue);
	}
}
