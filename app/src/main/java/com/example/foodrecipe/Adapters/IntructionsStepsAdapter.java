//package com.example.foodrecipe.Adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.foodrecipe.Models.Step;
//import com.example.foodrecipe.R;
//
//import java.util.List;
//
//public class IntructionsStepsAdapter extends RecyclerView.Adapter<InstructionStepsViewHolder> {
//
//    Context context;
//    List<Step> list;
//
//    public IntructionsStepsAdapter(Context context, List<Step> list) {
//        this.context = context;
//        this.list = list;
//    }
//
//    @NonNull
//    @Override
//    public InstructionStepsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new InstructionStepsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions,parent,false));
//
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull InstructionStepsViewHolder holder, int position) {
//
//        holder.textView_instruction_steps_no.setText(String.valueOf(list.get(position).number));
//        holder.textView_instruction_steps_title.setText(list.get(position).step);
//
//        holder.recycler_instructions_ingridients.setHasFixedSize(true);
//        holder.recycler_instructions_ingridients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//        InstructionsIngredientsAdapter instructionsIngredientsAdapter = new InstructionsIngredientsAdapter(context, list.get(position).ingredients);
//        holder.recycler_instructions_ingridients.setAdapter(instructionsIngredientsAdapter);
//
//        holder.recycler_instructions_equipments.setHasFixedSize(true);
//        holder.recycler_instructions_equipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
//        InstructionsEquipmentsAdapter instructionsEquipmentsAdapter = new InstructionsEquipmentsAdapter(context, list.get(position).equipment);
//        holder.recycler_instructions_equipments.setAdapter(instructionsEquipmentsAdapter);
//
//
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//}
//
//class InstructionStepsViewHolder extends RecyclerView.ViewHolder{
//
//    TextView textView_instruction_steps_no,textView_instruction_steps_title;
//    RecyclerView recycler_instructions_equipments,recycler_instructions_ingridients;
//
//    public InstructionStepsViewHolder(@NonNull View itemView) {
//        super(itemView);
//
//        textView_instruction_steps_no = itemView.findViewById(R.id.textView_instruction_steps_no);
//        textView_instruction_steps_title = itemView.findViewById(R.id.textView_instruction_steps_title);
//        recycler_instructions_equipments = itemView.findViewById(R.id.recycler_instructions_equipments);
//        recycler_instructions_ingridients = itemView.findViewById(R.id.recycler_instructions_ingridients);
//
//
//    }
//}
