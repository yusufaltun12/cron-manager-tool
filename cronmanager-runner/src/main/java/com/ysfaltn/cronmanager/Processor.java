package com.ysfaltn.cronmanager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.joda.time.LocalDateTime;

import com.ysfaltn.cronmanager.entity.ResultEntity;
import com.ysfaltn.cronmanager.model.ResultStatus;

public class Processor
{
	public static ResultEntity process(List<String> commands)
	{
		ProcessBuilder pb = new ProcessBuilder();
		pb.command(commands);
		ResultEntity result = new ResultEntity();

		pb.redirectErrorStream(true);
		try
		{
			Process p = pb.start();
			InputStream is = p.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder output = new StringBuilder();
			for (String line = br.readLine(); line != null; line = br.readLine())
			{
				output.append(line).append("<br>");
			}
			int error = p.waitFor();
			if (error == 1)
			{
				result.setStatus(ResultStatus.ERROR.toString());
			} else
			{
				result.setStatus(ResultStatus.SUCCESS.toString());
			}
			result.setDescription(output.toString());

		} catch (Exception e)
		{
			result.setStatus(ResultStatus.SYSTEM_ERROR.toString());
			result.setDescription(e.toString());
		}
		result.setCreatedAt(LocalDateTime.now());
		return result;
	}
}
