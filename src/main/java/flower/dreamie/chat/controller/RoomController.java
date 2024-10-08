package flower.dreamie.chat.controller;

import flower.dreamie.chat.entity.ChatRoom;
import flower.dreamie.chat.repository.ChatRoomRepository;
import flower.dreamie.chat.service.ChatService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;
@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/chat")
@Log4j2
public class RoomController {

    @Autowired
    private ChatRoomRepository repository;

    @Autowired
    private ChatService chatService;

    // 채팅방 목록 조회
    @GetMapping(value = "/rooms")
    public ModelAndView rooms() {
        log.info("# All Chat Rooms");
        ModelAndView mv = new ModelAndView("chat/rooms");

        List<ChatRoom> rooms = repository.findAll();
        mv.addObject("list", rooms);

        return mv;
    }

    // 채팅방 개설
    @PostMapping(value = "/createRoom")
    public String createRoom(@RequestParam String room_name, RedirectAttributes rttr) {

        ChatRoom room = new ChatRoom();
        room.setRoom_name(room_name);
        room.setCreatedAt(LocalDateTime.now());
        chatService.createChatRoom(room);

        rttr.addFlashAttribute("roomName", room);
        return "redirect:/chat/rooms";
    }

    // 채팅방 조회
    @GetMapping("/room")
    public String getRoom(@RequestParam String roomId, Model model, HttpSession session) {
        String id = (String) session.getAttribute("id");
        ChatRoom room = repository.findById(roomId).orElse(null);
        model.addAttribute("room", room);
        model.addAttribute("id", id);

        return "/chat/chat";

    }

    @PostMapping("/deleteRoom")
    public String deleteRoom(@RequestParam String roomId){
        chatService.RemoveRooms(roomId);
        return "redirect:/chat/rooms";
    }

}
