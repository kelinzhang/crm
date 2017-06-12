package com._520it.crm.web.controller;

import com._520it.crm.domain.Member;
import com._520it.crm.domain.Pet;
import com._520it.crm.domain.Recharge;
import com._520it.crm.domain.VipLevel;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PetQueryObject;
import com._520it.crm.service.IMemberService;
import com._520it.crm.service.IPetService;
import com._520it.crm.service.IRechargeService;
import com._520it.crm.service.IVipLevelService;
import com._520it.crm.util.AjaxResult;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@Controller
@RequestMapping("/pet")
public class PetController {
	@Autowired
	IPetService petService;
	@Autowired
    IMemberService memberService;
	@Autowired
	IRechargeService rechargeService;
	@Autowired
	IVipLevelService vipLevelService;

	@RequestMapping("")
	public String index(){
		return "pet";
	}
	@RequestMapping("/list")
	@ResponseBody
	public PageResult list(PetQueryObject qo){
		PageResult pageResult = null;
		pageResult = petService.queryByConditionPage(qo);
		return pageResult;
	}
	@RequestMapping("/save")
	@ResponseBody
	public AjaxResult save( Pet pet, @ModelAttribute("mem")Member member){
		AjaxResult result = null;
		try{
			member = memberService.selectByPrimaryKey(member.getId());
			pet.setMember(member);
			petService.insert(pet);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/savePetAndMember")
	@ResponseBody
	public AjaxResult savePetAndMember(Pet pet, Member member, Recharge recharge){
		AjaxResult result = null;
		try{
			memberService.insertForPet(member);
			rechargeService.insertForMember(member,recharge);
			petService.insertForMember(pet,member);
			result = new AjaxResult(true,"保存成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("保存失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/savePetAndMemberExcel")
	@ResponseBody
	public AjaxResult savePetAndMemberExcel(MultipartFile excel){
		AjaxResult result = null;
		try{
            InputStream in = excel.getInputStream();
            HSSFWorkbook wb = new HSSFWorkbook(in);
            HSSFSheet sheet = wb.getSheetAt(0);
            int rowNum = sheet.getLastRowNum();
            Row memberRow = null;
            for (int i =1;i<rowNum;i++){
                memberRow = sheet.getRow(i);
                System.out.println(getCellValue(memberRow.getCell(0))+"\t");
                System.out.println(getCellValue(memberRow.getCell(1))+"\t");
                System.out.println(getCellValue(memberRow.getCell(2))+"\t");
                System.out.println(getCellValue(memberRow.getCell(3))+"\t");
                System.out.println(getCellValue(memberRow.getCell(4))+"\t");
                System.out.println(getCellValue(memberRow.getCell(5))+"\t");
                System.out.println(getCellValue(memberRow.getCell(6))+"\t");
                System.out.println(getCellValue(memberRow.getCell(7))+"\t");
                System.out.println(getCellValue(memberRow.getCell(8))+"\t");
                System.out.println();
            }
            result = new AjaxResult(true,"导入成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("导入失败,请联系管理员！");
		}
		return result;
	}

    private Object getCellValue(Cell cell) {
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_STRING:
                return cell.getRichStringCellValue().getString();
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)){
                    return cell.getDateCellValue();
                }else {
                    return cell.getNumericCellValue();
                }
                case Cell.CELL_TYPE_BOOLEAN:
                    return cell.getBooleanCellValue();
                case Cell.CELL_TYPE_FORMULA:
                    return cell.getCellFormula();
                default:
                    return null;
        }
    }

    @RequestMapping("/updatePic")
	@ResponseBody
	public AjaxResult update(MultipartFile p,Pet pet){
		AjaxResult result = null;
		try{
			if(p!=null) {
				//String path = FileUploadUtil.uploadFile(p.getInputStream(),p.getName());
				//pet.setPic(path);
			}
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/updatePetAndMember")
	@ResponseBody
	public AjaxResult updatePetAndMember(Pet pet,@ModelAttribute("mem")Member member){
		AjaxResult result = null;
		try{
            memberService.updateForPet(member);
            petService.updateForMember(pet,member);
			result = new AjaxResult(true,"更新成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("更新失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/delete")
	@ResponseBody
	public AjaxResult delete(Long petId){
		AjaxResult result = null;
		try{
			petService.deleteByPrimaryKey(petId);
			result = new AjaxResult(true,"删除成功");
		}catch(Exception e){
			e.printStackTrace();
			result = new AjaxResult("删除失败,请联系管理员！");
		}
		return result;
	}
	@RequestMapping("/queryLevelByPet")
	@ResponseBody
	public VipLevel queryLevelByPet(Long petId){
		Long memberId = petService.selectMemberById(petId);
		VipLevel level =  memberService.selectByPrimaryKey(memberId).getLevel();
		 return  vipLevelService.selectByPrimaryKey(level.getId());
	}
	@InitBinder("mem")
	public void initBinder(WebDataBinder binder){
		binder.setFieldDefaultPrefix("member.");
	}
}
