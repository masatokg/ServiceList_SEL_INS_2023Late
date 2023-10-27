package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.entity.Member;
import com.example.demo.form.MemberForm;
import com.example.demo.service.MemberService;

@Controller
@SessionAttributes(types=MemberForm.class)
public class MemberContoller {
	// サービスのメソッドをあちこちで使えるひょうにインスタンス変数を用意
	@Autowired
	MemberService service;
	
	@GetMapping("index")
	public String indexView(MemberForm form) {
		// セッションの値をクリア
		form.setId(null);
		form.setName(null);
		form.setBusyo(null);
		return "index";
	}
	// dbselectというurl要求に応じてDBを検索して結果をビューに埋め込んで返す
//	@PostMapping("dbselect")
	@PostMapping(value="menu", params="select")
	public String listView( Model model ) {
		// サービスのメソッドでDB検索結果を取得
		Iterable<Member> list = service.findAll();
		// 取得したDB検索結果をSpringから引き渡されたModelインターフェースに追加
		model.addAttribute("list", list);
		// 次に表示するビュー名をブラウザに返す
		return "list";
	}
	// 登録入力画面を返す処理
	@PostMapping(value="menu", params="insert")
	public String memberInputView() {
		return "member_input";
	}
	// 登録の入力値を使ってDB処理して結果画面を返す処理
	@PostMapping("confirm")
	public String memberConfirmView(MemberForm form) {
		return "member_confirm";
	}
	@PostMapping("insert")
	public String memberCompleteView(MemberForm form) {
		// 入力フォームデータからリポジトリのSQLメソッドで使うMemberクラスのデータをセットする
		Member  member;
		member = new Member(
				form.getId(), form.getName(), form.getBusyo());
		service.insertMember(member);
		return "member_complete";
	}
}
